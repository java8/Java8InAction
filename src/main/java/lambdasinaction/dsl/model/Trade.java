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

package lambdasinaction.dsl.model;

public class Trade {

    public enum Type { BUY, SELL }

    private Type type;

    private Stock stock;

    private int quantity;

    private double price;

    public Type getType() {
        return type;
    }

    public void setType( Type type ) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity( int quantity ) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice( double price ) {
        this.price = price;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock( Stock stock ) {
        this.stock = stock;
    }

    public double getValue() {
        return quantity * price;
    }
}
