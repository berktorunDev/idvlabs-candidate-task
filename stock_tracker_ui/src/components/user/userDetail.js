import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import userService from "../../services/userService";

const UserDetail = () => {
  const { id } = useParams();
  const [user, setUser] = useState({});

  useEffect(() => {
    userService.getUserById(id).then((response) => {
      setUser(response.data);
    });
  }, [id]);

  return (
    <div>
      <h1>User Detail</h1>
      <p>Name: {user.name}</p>
      <p>Email: {user.email}</p>
    </div>
  );
};

export default UserDetail;
