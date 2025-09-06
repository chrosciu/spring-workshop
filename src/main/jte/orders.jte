@import java.util.List
@import eu.chrost.shop.orders.OrderOutputDto
@import static java.util.stream.Collectors.joining

@param List<OrderOutputDto> orders

<div>
    @if(orders.isEmpty())
        <h2>No orders yet!</h2>
    @else        
        <div>
            <h2>Orders</h2>
            <table>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Products</th>
                </tr>
                </thead>
                <tbody>
                    @for(OrderOutputDto order : orders)
                        <tr>
                            <td>${order.getId()}</td>
                            <td>${order.getProductsIds().stream().map(String::valueOf).collect(joining(","))}</td>
                        </tr>
                    @endfor                
                </tbody>
            </table>
        </div>
    @endif
    <a href="/orders/new">New order</a>
</div>


