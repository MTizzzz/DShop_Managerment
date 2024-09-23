import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useShoppingContext } from "../contexts/ShoppingContext";
import { formatCurrency } from "../helpers/common";
import axios from "axios";

const Checkout = () => {
  const navigate = useNavigate();
  const {
    cartItems,
    totalPrice,
    increaseQty,
    decreaseQty,
    removeCartItem,
    clearCart,
  } = useShoppingContext();

  // State to manage form inputs
  const [orderDetails, setOrderDetails] = useState({
    total: totalPrice,
    fullName: "",
    address: "",
    phone: "",
    orderDate: new Date().toISOString().split("T")[0], // Current date in YYYY-MM-DD format
    saleDate: new Date().toISOString().split("T")[0],  // Current date in YYYY-MM-DD format
    status: "Ordered", // or any default status
    note: "",
    productRequests: cartItems.map(item => ({
      idPro: item.productId,
      amount: item.qty,
      price: item.price,
      discount: item.discount || 0, // assuming discount field in cart item
    })),
  });

  // Handle input changes
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setOrderDetails({
      ...orderDetails,
      [name]: value,
    });
  };

  // Handle form submission
  const handleFormSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      // Make a POST request to the create-order API
      const response = await axios.post(
        "http://localhost:8080/api/v1/order/create-order",
        orderDetails
      );
      alert("Order placed successfully!");
      clearCart();
      navigate("/products");
    } catch (error) {
      console.error("Error placing order:", error);
      alert("Failed to place the order.");
    }
  };

  return (
    <div className="row">
      <h3>Checkout</h3>
      <table className="table table-hover">
        <thead>
          <tr>
            <th>Image</th>
            <th>Name</th>
            <th>Price</th>
            <th>Discount</th>
            <th>Quantity</th>
            <th>Total</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {cartItems.map((item) => (
            <tr key={item.productId}>
              <td>
                <img
                  src={item.image}
                  className="img-fluid rounded"
                  alt={item.productName}
                />
              </td>
              <td>{item.productName}</td>
              <td>{formatCurrency(item.price)}</td>
              <td>{(item.discount) + "%"}</td>
              <td style={{ width: "100px" }}>
                {item.qty}
                <button
                  type="button"
                  className="btn btn-sm btn-primary ms-1 me-1"
                  onClick={() => decreaseQty(item.productId)}
                >
                  <strong>-</strong>
                </button>
                <button
                  type="button"
                  className="btn btn-sm btn-primary"
                  onClick={() => increaseQty(item.productId)}
                >
                  <strong>+</strong>
                </button>
              </td>
              <td>{formatCurrency(item.price * item.qty * (1 - item.discount / 100))}</td>
              <td>
                <button
                  className="btn btn-sm btn-danger btn-remove"
                  onClick={() => removeCartItem(item.productId)}
                >
                  <i className="fas fa-trash-alt"></i>
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className="col-md-12">
        <span className="float-end me-2">
          <strong>Total: {formatCurrency(totalPrice)}</strong>
        </span>
      </div>

      <form onSubmit={handleFormSubmit} className="col-md-12 mt-4">
        <div className="mb-3">
          <label htmlFor="fullName" className="form-label">
            Full Name
          </label>
          <input
            type="text"
            className="form-control"
            id="fullName"
            name="fullName"
            value={orderDetails.fullName}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="address" className="form-label">
            Address
          </label>
          <input
            type="text"
            className="form-control"
            id="address"
            name="address"
            value={orderDetails.address}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="phone" className="form-label">
            Phone
          </label>
          <input
            type="tel"
            className="form-control"
            id="phone"
            name="phone"
            value={orderDetails.phone}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="note" className="form-label">
            Note
          </label>
          <textarea
            className="form-control"
            id="note"
            name="note"
            value={orderDetails.note}
            onChange={handleInputChange}
          />
        </div>
        <div className="mt-3">
          <Link to="/products" className="btn btn-sm btn-primary float-start">
            Continue shopping
          </Link>
          <button
            type="submit"
            className="btn btn-sm btn-success float-end me-2 d-block"
          >
            Place Order
          </button>
        </div>
      </form>
    </div>
  );
};

export default Checkout;
