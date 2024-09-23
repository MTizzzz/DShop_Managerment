import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import userService from "../services/userService"; // Giả sử userService đã được triển khai đúng cách

type User = {
  userId: number;
  username: string;
  email: string;
  role: string;
  avatar: string;
};

const AdminUser: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string>("");

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const { data } = await userService.getAll(); // Không cần tham số
        setUsers(data); // Giả sử dữ liệu trả về là danh sách người dùng trực tiếp
        setError("");
      } catch (error) {
        console.error("Lỗi khi lấy danh sách người dùng:", error);
        setError("Không thể tải người dùng.");
      } finally {
        setLoading(false);
      }
    };

    fetchUsers();
  }, []);

  const handleDelete = async (userId: number) => {
    try {
      await userService.delete(userId);
      setUsers(users.filter((user) => user.userId !== userId));
    } catch (error) {
      console.error("Lỗi khi xóa người dùng:", error);
      setError("Không thể xóa người dùng.");
    }
  };

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    // Nếu bạn không cần tìm kiếm thì có thể bỏ qua hàm này
  };

  const handleRoleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    // Nếu bạn không cần lọc theo vai trò thì có thể bỏ qua hàm này
  };

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div className="alert alert-danger">{error}</div>;
  }

  return (
    <div className="container user-list">
      <h3 className="text-center my-4">User Management</h3>
      <Link to="/admin/users/create" className="btn btn-primary mb-3">
        Add New User
      </Link>

      <table className="table table-hover">
        <thead>
          <tr>
            <th>ID</th>
            <th>Avatar</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th style={{ width: "123px", display: "flex" }}>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.userId}>
              <td>{user.userId}</td>
              <td>
                <img
                  src={user.avatar}
                  alt={user.username}
                  style={{ width: "50px", height: "50px", objectFit: "cover" }}
                />
              </td>
              <td>{user.username}</td>
              <td>{user.email}</td>
              <td>{user.role}</td>
              <td>
                <button
                  onClick={() => handleDelete(user.userId)}
                  className="btn btn-danger btn-sm"
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminUser;