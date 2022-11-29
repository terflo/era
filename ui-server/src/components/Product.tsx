import React from 'react'
import {IProduct} from "./interfaces/IProduct";

interface ProductProps {
    product: IProduct
}

export function Product(props: ProductProps) {
    return (
        <div>
            <h2>{props.product.name}</h2>
            <h3>{props.product.description}</h3>
        </div>
    )
}