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

import lambdasinaction.dsl.model.Order;
import lambdasinaction.dsl.model.Stock;
import lambdasinaction.dsl.model.Trade;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class MixedBuilder {

    public static Order forCustomer(String customer, TradeBuilder... builders) {
        Order order = new Order();
        order.setCustomer( customer );
        Stream.of(builders).forEach( b -> order.addTrade( b.trade ) );
        return order;
    }

    public static TradeBuilder buy(Consumer<TradeBuilder> consumer) {
        return buildTrade( consumer, Trade.Type.BUY );
    }

    public static TradeBuilder sell(Consumer<TradeBuilder> consumer) {
        return buildTrade( consumer, Trade.Type.SELL );
    }

    private static TradeBuilder buildTrade( Consumer<TradeBuilder> consumer, Trade.Type buy ) {
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType( buy );
        consumer.accept( builder );
        return builder;
    }

    public static class TradeBuilder {
        private Trade trade = new Trade();

        public TradeBuilder quantity(int quantity) {
            trade.setQuantity( quantity );
            return this;
        }

        public TradeBuilder at(double price) {
            trade.setPrice( price );
            return this;
        }

        public StockBuilder stock(String symbol) {
            return new StockBuilder(this, trade, symbol);
        }
    }

    public static class StockBuilder {
        private final TradeBuilder builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        private StockBuilder(TradeBuilder builder, Trade trade, String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol( symbol );
        }

        public TradeBuilder on(String market) {
            stock.setMarket( market );
            trade.setStock( stock );
            return builder;
        }
    }
}
