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

import java.util.stream.Stream;

public class NestedFunctionOrderBuilder {

    public static Order order(String customer, Trade... trades) {
        Order order = new Order();
        order.setCustomer( customer );
        Stream.of(trades).forEach( order::addTrade );
        return order;
    }

    public static Trade buy(int quantity, Stock stock, double price) {
        return buildTrade( stock, price, Trade.Type.BUY );
    }

    public static Trade sell(int quantity, Stock stock, double price) {
        return buildTrade( stock, price, Trade.Type.SELL );
    }

    private static Trade buildTrade( Stock stock, double price, Trade.Type buy ) {
        Trade trade = new Trade();
        trade.setType( buy );
        trade.setStock( stock );
        trade.setPrice( price );
        return trade;
    }

    public static double at(double price) {
        return price;
    }

    public static Stock stock(String symbol, String market) {
        Stock stock = new Stock();
        stock.setSymbol( symbol );
        stock.setMarket( market );
        return stock;
    }

    public static String on(String market) {
        return market;
    }
}
