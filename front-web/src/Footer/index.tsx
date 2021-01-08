import './style.css';
import { ReactComponent as Instagram } from './instagram.svg';
import { ReactComponent as Youtube } from './youtube.svg';
import { ReactComponent as Linkedin } from './linkedin.svg';

function Footer() {
    return (
        <footer className="main-footer">
            App desenvolvido durante a 2ª ed. do evento Semana DevSuperior
            <div className="footer-icons">
                <a href="http://www.youtube.com/c/DevSuperior" target="_new">
                    <Youtube />
                </a>
                <a href="http://www.linkedin.com/school/devsuperior" target="_new">
                    <Linkedin />
                </a>
                <a href="http://www.instagram.com/devsuperior.ig" target="_new">
                    <Instagram />
                </a>

            </div>
        </footer>
    )
}

export default Footer;