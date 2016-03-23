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

public class MethodChainingOrderBuilder {

    public final Order order = new Order();

    private MethodChainingOrderBuilder(String customer) {
        order.setCustomer( customer );
    }

    public static MethodChainingOrderBuilder forCustomer( String customer ) {
        return new MethodChainingOrderBuilder(customer);
    }

    public Order end() {
        return order;
    }

    public TradeBuilder buy(int quantity) {
        return new TradeBuilder( this, Trade.Type.BUY, quantity );
    }

    public TradeBuilder sell(int quantity) {
        return new TradeBuilder( this, Trade.Type.SELL, quantity );
    }

    private MethodChainingOrderBuilder addTrade(Trade trade) {
        order.addTrade( trade );
        return this;
    }

    public static class TradeBuilder {
        private final MethodChainingOrderBuilder builder;
        public final Trade trade = new Trade();

        private TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) {
            this.builder = builder;
            trade.setType( type );
            trade.setQuantity( quantity );
        }

        public StockBuilder stock(String symbol) {
            return new StockBuilder( builder, trade, symbol );
        }
    }

    public static class TradeBuilderWithStock {
        private final MethodChainingOrderBuilder builder;
        private final Trade trade;

        public TradeBuilderWithStock( MethodChainingOrderBuilder builder, Trade trade ) {
            this.builder = builder;
            this.trade = trade;
        }

        public MethodChainingOrderBuilder at(double price) {
            trade.setPrice( price );
            return builder.addTrade( trade );
        }
    }

    public static class StockBuilder {
        private final MethodChainingOrderBuilder builder;
        private final Trade trade;
        private final Stock stock = new Stock();

        private StockBuilder(MethodChainingOrderBuilder builder, Trade trade, String symbol) {
            this.builder = builder;
            this.trade = trade;
            stock.setSymbol( symbol );
        }

        public TradeBuilderWithStock on(String market) {
            stock.setMarket( market );
            trade.setStock( stock );
            return new TradeBuilderWithStock( builder, trade );
        }
    }
}
