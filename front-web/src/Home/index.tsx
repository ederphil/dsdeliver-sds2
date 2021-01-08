import './style.css';
import {ReactComponent as MainImage} from './main.svg';

function home(){
    return (
        <div className="home-container">
           <div className="home-content">
                <div className="home-actions">
                    <h1 className="home-title">Faça seu pedido <br/>que entregamos <br/> para você!!!</h1>
                    <h3 className="home-subtitle">Escolha seu pedido e em poucos minutos <br/>levaremos na sua porta!!!</h3>
                    <a href="orders" className="home-btn-order">
                        FAZER PEDIDO
                    </a>
                </div>
                <div className="home-image">
                    <MainImage/>
                </div>
            </div>
        </div>
    )
}

export default home;