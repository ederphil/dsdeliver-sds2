import { useEffect, useState } from 'react';
import { toast } from 'react-toastify';
import { fetchProducts, saveOrder } from '../api';
import Footer from '../Footer';
import { checkIsSelected } from './Helpers';
import OrderLocation from './OrderLocation';
import OrderSumary from './OrderSummary';
import ProductList from './ProductsList';
import StepsHeader from './StepsHeader';
import './style.css';
import { OrderLocationData, Product } from './Types';

function Orders() {

    const [products, setProducts] = useState<Product[]>([]);
    const [selectedProducts, setSelectedProducts] = useState<Product[]>([]);
    const [orderLocation, setOrderLocation] = useState<OrderLocationData>();
    const totalPrice = selectedProducts.reduce((sum, item) => {
        return sum + item.price;
    }, 0)

    const handleSubmit = () => {
        const productsIds = selectedProducts.map(({ id }) => ({ id }));
        const payload = {
            ...orderLocation!,
            products: productsIds
        }

        saveOrder(payload).then((reponse) => {
            toast.error(`Pedido enviado com sucesso! Nº ${reponse.data.id}`);
            setSelectedProducts([]);
        })
            .catch(() => {
                toast.warning('Erro ao enviar pedido');
            })
    }

    console.log(products);

    useEffect(() => {
        fetchProducts()
            .then(response => setProducts(response.data))
            .catch(error => toast.warning('Erro ao listar produtos.'))

    }, []);

    const handleSelectProduct = (product: Product) => {
        const isAlreadySelected = checkIsSelected(selectedProducts, product);

        if (isAlreadySelected) {
            const selected = selectedProducts.filter(item => item.id !== product.id);
            setSelectedProducts(selected);
        } else {
            setSelectedProducts(previous => [...previous, product]);
        }
    }

    return (
        <>
            <div className="orders-container">
                <StepsHeader />
                <ProductList
                    products={products}
                    onSelectProduct={handleSelectProduct}
                    selectedProducts={selectedProducts}
                />
                <OrderLocation
                    onChangeLocation={location => setOrderLocation(location)}
                />
                <OrderSumary
                    amount={selectedProducts.length}
                    totalPrice={totalPrice}
                    onSubmit={handleSubmit}
                />
                <Footer />
            </div>

        </>
    )
}

export default Orders;