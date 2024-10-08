import React from "react";
import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faFacebook,
  faYoutube,
  faTiktok,
} from "@fortawesome/free-brands-svg-icons";
import "../components/Footer.scss"; // Đảm bảo bạn đã nhập tệp CSS của mình

const Footer = () => {
  return (
    <footer className="bg-light text-center text-lg-start">
      <div className="container p-4">
        <div className="row">
          <div className="col-lg-6 col-md-12 mb-4 mb-md-0">
            <h5 className="text-uppercase">THÔNG TIN CÔNG TY</h5>
            <p>
              CÔNG TY CỔ PHẦN DShop
            </p>
          </div>
          <div className="col-lg-3 col-md-6 mb-4 mb-md-0">
            <h5 className="text-uppercase">Hướng dẫn</h5>
            <ul className="list-unstyled mb-0">
              <li>
                <Link to="/" className="text-dark">
                  Home
                </Link>
              </li>
              <li>
                <Link to="/about" className="text-dark">
                  About
                </Link>
              </li>
              <li>
                <Link to="/services" className="text-dark">
                  Services
                </Link>
              </li>
              <li>
                <Link to="/contact" className="text-dark">
                  Contact
                </Link>
              </li>
            </ul>
          </div>
          <div className="col-lg-3 col-md-6 mb-4 mb-md-0">
            <h5 className="text-uppercase">Liên hệ</h5>
            <ul className="list-unstyled mb-0">
              <li>
                <a href="mailto:trcuong666@example.com" className="text-dark">
                  ducphamvkml@gmail.com
                </a>
              </li>
              <li>
                <a href="+84.987654321" className="text-dark">
                  +84.987654321
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div className="row mt-4">
          <div className="col text-center">
            <a
              href="https://www.facebook.com"
              className="footer-icon facebook mx-2"
              aria-label="Facebook"
            >
              <FontAwesomeIcon icon={faFacebook} size="2x" />
            </a>
            <a
              href="https://www.youtube.com"
              className="footer-icon youtube mx-2"
              aria-label="YouTube"
            >
              <FontAwesomeIcon icon={faYoutube} size="2x" />
            </a>
            <a
              href="https://www.tiktok.com"
              className="footer-icon tiktok mx-2"
              aria-label="TikTok"
            >
              <FontAwesomeIcon icon={faTiktok} size="2x" />
            </a>
          </div>
        </div>
      </div>
      <div
        className="text-center p-3"
        style={{ backgroundColor: "rgba(0, 0, 0, 0.2)" }}
      >
        ©2024 - Bản quyền MTizz
      </div>
    </footer>
  );
};

export default Footer;
