@import eu.chrost.shop.common.web.JteValidationHelper
@import eu.chrost.shop.orders.OrderInputDto
@import eu.chrost.shop.products.ProductOutputDto
@import java.util.List

@param OrderInputDto order
@param List<ProductOutputDto> products
@param JteValidationHelper validation

<link href="/styles.css" rel="stylesheet" />
<form action="/orders" method="post">
    <p>
        <label for="productIds">Products</label>
        <select id="productIds" name="productsIds" multiple>
            @for(ProductOutputDto product : products)
                <option value="${product.getId()}">${product.getName()}</option>
            @endfor            
        </select>
        @if(validation.hasError("productsIds"))
            <span class="error">${validation.getError("productsIds").getDefaultMessage()}</span>
        @endif
    </p>
    <p>
        <input type="submit" value="Place order">
    </p>
</form>