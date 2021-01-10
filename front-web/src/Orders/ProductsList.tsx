import { checkIsSelected } from './Helpers';
import ProductCard from './ProductCard';
import './style.css';
import { Product } from './Types';

type Props = {
    products: Product[];
    onSelectProduct: (product: Product) => void;
    selectedProducts: Product[];
}

function ProductList({ products, onSelectProduct, selectedProducts }: Props) {
    return (

        <div className="orders-list-container">
            <div className="orders-list-items">
                {products.map(product => (
                    <ProductCard
                        key={product.id}
                        product={product}
                        onSelectProduct={onSelectProduct} 
                        isSelected={checkIsSelected(selectedProducts, product)}
                        />
                ))}
            </div>
        </div>
    )
}

export default ProductList;