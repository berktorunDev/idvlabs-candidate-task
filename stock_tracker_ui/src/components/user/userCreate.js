import React, { useState } from "react";
import userService from "../../services/userService";

const UserCreate = () => {
  const [newUser, setNewUser] = useState({ name: "", email: "" });

  const handleCreateUser = () => {
    userService.createUser(newUser).then(() => {
      // Handle success or redirect to the user list
    });
  };

  return (
    <div>
      <h1>Create User</h1>
      <div>
        <input
          type="text"
          placeholder="Name"
          value={newUser.name}
          onChange={(e) => setNewUser({ ...newUser, name: e.target.value })}
        />
        <input
          type="text"
          placeholder="Email"
          value={newUser.email}
          onChange={(e) => setNewUser({ ...newUser, email: e.target.value })}
        />
        <button onClick={handleCreateUser}>Create</button>
      </div>
    </div>
  );
};

export default UserCreate;
