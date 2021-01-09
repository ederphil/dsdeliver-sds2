import { useEffect, useState } from 'react';
import { fetchProducts } from '../api';
import ProductList from './ProductsList';
import StepsHeader from './StepsHeader';
import './style.css';
import { Product } from './Types';

function Orders() {

    const [products, setProducts] = useState<Product[]>([]);
    console.log(products);
    
    useEffect(() => {
        fetchProducts()
            .then(response => setProducts(response.data))
            .catch(error => console.log(error))

    }, []);

    return (
        <div className="orders-container">
            <StepsHeader />
            <ProductList products={products} />
        </div>
    )
}

export default Orders;