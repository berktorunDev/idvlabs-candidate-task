import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import userService from "../../services/userService";

const UserUpdate = () => {
  const { id } = useParams();
  const [user, setUser] = useState({});
  const [updatedUser, setUpdatedUser] = useState({ name: "", email: "" });

  useEffect(() => {
    userService.getUserById(id).then((response) => {
      setUser(response.data);
      setUpdatedUser(response.data);
    });
  }, [id]);

  const handleUpdateUser = () => {
    userService.updateUser(id, updatedUser).then(() => {
      // Handle success or redirect to the user list
    });
  };

  return (
    <div>
      <h1>Update User</h1>
      <div>
        <input
          type="text"
          placeholder="Name"
          value={updatedUser.name}
          onChange={(e) =>
            setUpdatedUser({ ...updatedUser, name: e.target.value })
          }
        />
        <input
          type="text"
          placeholder="Email"
          value={updatedUser.email}
          onChange={(e) =>
            setUpdatedUser({ ...updatedUser, email: e.target.value })
          }
        />
        <button onClick={handleUpdateUser}>Update</button>
      </div>
    </div>
  );
};

export default UserUpdate;
