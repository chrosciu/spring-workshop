@import eu.chrost.shop.products.ProductOutputDto
@import java.util.List

@param List<ProductOutputDto> products

<div>
    @if(products.isEmpty())
        <h2>No products yet!</h2>
    @else
        <div>
            <h2>Products</h2>
            <table>
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Type</th>
                </tr>
                </thead>
                <tbody>
                    @for(ProductOutputDto product : products)
                        <tr>
                            <td>${product.getName()}</td>
                            <td>${product.getPrice()}</td>
                            <td>${product.getType()}</td>
                        </tr>
                    @endfor
                </tbody>
            </table>
        </div>
    @endif
    <a href="/products/new">New product</a>
</div>