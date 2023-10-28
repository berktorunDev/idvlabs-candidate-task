import axios from "axios";

const BASE_URL_USER = "http://localhost:8080/api/public/users";

const api = axios.create({
  baseURL: BASE_URL_USER,
});

const updateUser = (id, userData) => api.put(`/update/${id}`, userData);

const createUser = (userData) => api.post("/create", userData);

const getUserById = (id) => api.get(`/${id}`);

const getAllUsers = () => api.get("/getAll");

const deleteUser = (id) => api.delete(`/delete/${id}`);

export default {
  updateUser,
  createUser,
  getUserById,
  getAllUsers,
  deleteUser,
};
