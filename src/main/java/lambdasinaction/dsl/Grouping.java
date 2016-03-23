/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lambdasinaction.dsl;

import lambdasinaction.chap6.Dish;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

import static java.util.stream.Collectors.groupingBy;
import static lambdasinaction.chap6.Dish.menu;
import static lambdasinaction.dsl.Grouping.GroupingBuilder.groupOn;

public class Grouping {

    enum CaloricLevel { DIET, NORMAL, FAT };

    public static void main(String ... args) {
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel2());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel3());
    }

    private static CaloricLevel getCaloricLevel( Dish dish ) {
        if (dish.getCalories() <= 400) return CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
        else return CaloricLevel.FAT;
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel2() {
        return menu.stream().collect(
                twoLevelGroupingBy(Dish::getType, dish -> getCaloricLevel( dish ) )
                                    );
    }

    public static <A, B, T> Collector<T, ?, Map<A, Map<B, List<T>>>> twoLevelGroupingBy(Function<? super T, ? extends A> f1, Function<? super T, ? extends B> f2) {
        return groupingBy(f1, groupingBy(f2));
    }

    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel3() {
        Collector<? super Dish, ?, Map<Dish.Type, Map<CaloricLevel, List<Dish>>>> c = groupOn( ( Dish dish ) -> getCaloricLevel( dish ) ).after( Dish::getType ).get();
        return menu.stream().collect( c );
    }

    public static class GroupingBuilder<T, D, K> {
        private final Collector<? super T, ?, Map<K, D>> collector;

        public GroupingBuilder( Collector<? super T, ?, Map<K, D>> collector ) {
            this.collector = collector;
        }

        public Collector<? super T, ?, Map<K, D>> get() {
            return collector;
        }

        public <J> GroupingBuilder<T, Map<K, D>, J> after(Function<? super T, ? extends J> classifier) {
            return new GroupingBuilder<>( groupingBy( classifier, collector ) );
        }

        public static <T, D, K> GroupingBuilder<T, List<T>, K> groupOn(Function<? super T, ? extends K> classifier) {
            return new GroupingBuilder<>( groupingBy( classifier )  );
        }
    }
}
