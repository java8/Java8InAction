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

public class LambdaOrderBuilder {

    private Order order = new Order();

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {
        LambdaOrderBuilder builder = new LambdaOrderBuilder();
        consumer.accept( builder );
        return builder.order;
    }

    public void forCustomer(String customer) {
        order.setCustomer( customer );
    }

    public void buy(Consumer<TradeBuilder> consumer) {
        trade( consumer, Trade.Type.BUY );
    }

    public void sell(Consumer<TradeBuilder> consumer) {
        trade( consumer, Trade.Type.SELL );
    }

    private void trade( Consumer<TradeBuilder> consumer, Trade.Type type ) {
        TradeBuilder builder = new TradeBuilder();
        builder.trade.setType( type );
        consumer.accept( builder );
        order.addTrade( builder.trade );
    }

    public static class TradeBuilder {
        private Trade trade = new Trade();

        public void quantity(int quantity) {
            trade.setQuantity( quantity );
        }

        public void price(double price) {
            trade.setPrice( price );
        }

        public void stock(Consumer<StockBuilder> consumer) {
            StockBuilder builder = new StockBuilder();
            consumer.accept( builder );
            trade.setStock( builder.stock );
        }
    }

    public static class StockBuilder {
        private Stock stock = new Stock();

        public void symbol(String symbol) {
            stock.setSymbol( symbol );
        }

        public void market(String market) {
            stock.setMarket( market );
        }
    }
}
