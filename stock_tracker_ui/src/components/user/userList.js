import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import userService from "../../services/userService";

const UserList = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    userService.getAllUsers().then((response) => {
      setUsers(response.data);
    });
  }, []);

  const handleDeleteUser = (id) => {
    userService.deleteUser(id).then(() => {
      // Remove the deleted user from the local state
      setUsers((prevUsers) => prevUsers.filter((user) => user.id !== id));
    });
  };

  return (
    <div>
      <h1>User List</h1>
      <ul>
        {users.map((user) => (
          <li key={user.id}>
            <Link to={`/user/${user.id}`}>{user.name}</Link>
            <button onClick={() => handleDeleteUser(user.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default UserList;
