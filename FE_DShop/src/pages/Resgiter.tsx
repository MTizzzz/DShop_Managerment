import React, { useState } from "react";
import { Container, Row, Col, Form, FormGroup, Button } from "reactstrap";
import { Link } from "react-router-dom";
import "../pages/Register.scss";
import register from "../assests/images/register.png";
import userIcon from "../assests/images/user.png";
import { useNavigate } from "react-router-dom";

const Register = () => {
  const navigate = useNavigate();

  const baseUrlAuth = "http://localhost:8080/api"; // Update this with your backend URL
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [email, setEmail] = useState("");
  const [fullName, setFullName] = useState("");
  const [phone, setPhone] = useState("");
  const [image, setImage] = useState(""); // Add state for image
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleRegister = async (event: React.FormEvent) => {
    event.preventDefault();
    setLoading(true);
    setError("");

    const newUser = {
      username: username,
      password: password,
      email: email,
      fullName: fullName,
      phone: phone,
      image: image,
    };

    try {
      const response = await fetch(baseUrlAuth + "/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newUser),
      });

      if (response.ok) {
        alert("Đăng ký thành công!");
        navigate("/login"); // Redirect to login page
      } else {
        const errorData = await response.json();
        throw new Error(errorData.message || "Đăng ký không thành công.");
      }
    } catch (error) {
      console.error(error);
      setError((error as Error).message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <section>
      <Container>
        <Row>
          <Col lg="8" className="m-auto">
            <div className="register__container d-flex justify-content-between">
              <div className="register__img">
                <img style={{ marginTop: "60px" }} src={register} alt="" />
              </div>
              <div className="register__form">
                <div className="user">
                  <img src={userIcon} alt="" />
                </div>
                <h2>Register</h2>
                <Form onSubmit={handleRegister}>
                  <FormGroup>
                    <input
                      type="text"
                      placeholder="username"
                      required
                      value={username}
                      onChange={(e) => setUsername(e.target.value)}
                    />
                  </FormGroup>
                  <FormGroup>
                    <input
                      type="password"
                      placeholder="password"
                      required
                      value={password}
                      onChange={(e) => setPassword(e.target.value)}
                    />
                  </FormGroup>
                  <FormGroup>
                    <input
                      type="email"
                      placeholder="email"
                      required
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                    />
                  </FormGroup>
                  <FormGroup>
                    <input
                      type="text"
                      placeholder="fullName"
                      required
                      value={fullName}
                      onChange={(e) => setFullName(e.target.value)}
                    />
                  </FormGroup>
                  <FormGroup>
                    <input
                      type="text"
                      placeholder="phone"
                      required
                      value={phone}
                      onChange={(e) => setPhone(e.target.value)}
                    />
                  </FormGroup>
                  <FormGroup>
                    <input
                      type="text"
                      placeholder="image URL"
                      required
                      value={image}
                      onChange={(e) => setImage(e.target.value)}
                    />
                  </FormGroup>
                  <Button
                    style={{
                      color: "black",
                      backgroundColor: "white",
                    }}
                    className="btn"
                    type="submit"
                    disabled={loading}
                  >
                    {loading ? "Creating Account..." : "Create Account"}
                  </Button>
                </Form>
                {error && <p className="text-danger">{error}</p>}
                <p>
                  Bạn đã có tài khoản? <Link to="/login">Đăng Nhập</Link>
                </p>
              </div>
            </div>
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default Register;
