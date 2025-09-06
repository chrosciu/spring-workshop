@import eu.chrost.shop.common.web.JteValidationHelper
@import eu.chrost.shop.products.ProductInputDto
@import eu.chrost.shop.products.ProductType
@import org.springframework.validation.ObjectError

@param ProductInputDto product
@param JteValidationHelper validation

<link href="/styles.css" rel="stylesheet" />
<form action="/products" method="post">
    <p>
        <label for="name">Name</label>
        <input type="text" value="${product.getName()}" id="name" name="name" placeholder="Name">
        @if(validation.hasError("name"))
            <span class="error">${validation.getError("name").getDefaultMessage()}</span>
        @endif
    </p>
    <p>
        <label for="type">Product Type</label>
        <select id="type" name="type">
            @for(ProductType productType : ProductType.values())
                <option value="${productType}">${productType}</option>
            @endfor
        </select>
        @if(validation.hasError("type"))
            <span class="error">${validation.getError("type").getDefaultMessage()}</span>
        @endif
    </p>
    <p>
        <label for="price">Price</label>
        <input type="number" value="${product.getPrice()}" id="price" name="price" placeholder="0">
        @if(validation.hasError("price"))
            <span class="error">${validation.getError("price").getDefaultMessage()}</span>
        @endif
    </p>
    @if(validation.hasGlobalErrors())
        @for(ObjectError objectError : validation.getGlobalErrors())
            <p>
                <span class="error">${objectError.getDefaultMessage()}</span>
            </p>
        @endfor
    @endif
    <p>
        <input type="submit" value="Add Product">
    </p>
</form>