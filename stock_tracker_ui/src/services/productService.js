import axios from "axios";

const BASE_URL_STOCK = "http://localhost:8080/api/public/stocks";

const apiProduct = axios.create({
  baseURL: BASE_URL_PRODUCT,
});

const updateProduct = (id, productData) =>
  apiProduct.put(`/update/${id}`, productData);

const createProduct = (productData) => apiProduct.post("/create", productData);

const getProductById = (id) => apiProduct.get(`/${id}`);

const getAllProducts = () => apiProduct.get("/getAll");

const deleteProduct = (id) => apiProduct.delete(`/delete/${id}`);

export default {
  updateProduct,
  createProduct,
  getProductById,
  getAllProducts,
  deleteProduct,
};
