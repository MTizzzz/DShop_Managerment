import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import userService from "../services/userService";

type UserFormProps = {
    mode: "create" | "update";
};

const UserForm: React.FC<UserFormProps> = ({ mode }) => {
    const { userId } = useParams<{ userId: string }>();
    const navigate = useNavigate();
    const [userData, setUserData] = useState({
        username: "",
        password: "",
        email: "",
        fullName: "",
        image: "",
        phone: "",
    });
    const [error, setError] = useState<string>("");
    const [alert, setAlert] = useState<{ type: string; message: string } | null>(null);

    useEffect(() => {
        if (mode === "update" && userId) {
            const fetchUser = async () => {
                try {
                    const response = await userService.get(parseInt(userId));
                    const data = response.data;
                    setUserData({
                        username: data.username || "",
                        password: "",
                        email: data.email || "",
                        fullName: data.fullName || "",
                        image: data.image || "",
                        phone: data.phone || "",
                    });
                    setError("");
                } catch (error) {
                    console.error("Error fetching user:", error);
                    setError("Failed to load user data.");
                }
            };

            fetchUser();
        }
    }, [mode, userId]);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setUserData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            if (mode === "create") {
                await userService.create(userData);
                setAlert({
                    type: "success",
                    message: "User created successfully.",
                });
            } else if (mode === "update" && userId) {
                await userService.update(parseInt(userId), userData);
                setAlert({
                    type: "success",
                    message: "User updated successfully.",
                });
            }
            setTimeout(() => navigate("/admin/users"), 2000);
        } catch (error) {
            console.error("Error saving user:", error);
            setAlert({ type: "danger", message: "Failed to save user data." });
        }
    };

    return (
        <div className="container user-form">
            <h3 className="text-center my-4">
                {mode === "create" ? "Add New User" : "Edit User"}
            </h3>
            {alert && (
                <div className={`alert alert-${alert.type}`}>{alert.message}</div>
            )}
            {error && <div className="alert alert-danger">{error}</div>}
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="username" className="form-label">
                        Username
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="username"
                        name="username"
                        value={userData.username}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="password" className="form-label">
                        Password
                    </label>
                    <input
                        type="password"
                        className="form-control"
                        id="password"
                        name="password"
                        value={userData.password}
                        onChange={handleChange}
                        required={mode === "create"}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="email" className="form-label">
                        Email
                    </label>
                    <input
                        type="email"
                        className="form-control"
                        id="email"
                        name="email"
                        value={userData.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="fullName" className="form-label">
                        Full Name
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="fullName"
                        name="fullName"
                        value={userData.fullName}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="image" className="form-label">
                        Image URL
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="image"
                        name="image"
                        value={userData.image}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="phone" className="form-label">
                        Phone Number
                    </label>
                    <input
                        type="text"
                        className="form-control"
                        id="phone"
                        name="phone"
                        value={userData.phone}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div
                    style={{ display: "flex", justifyContent: "space-between" }}
                    className="button-form-user"
                >
                    <button type="submit" className="btn btn-primary">
                        {mode === "create" ? "Add User" : "Update User"}
                    </button>
                    <button
                        type="button"
                        className="btn btn-danger"
                        onClick={() => navigate("/admin/users")}
                    >
                        Cancel
                    </button>
                </div>
            </form>
        </div>
    );
};

export default UserForm;