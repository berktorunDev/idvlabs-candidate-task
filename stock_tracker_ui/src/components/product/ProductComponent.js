import React, { useEffect, useState } from 'react';
import productService from './productService';

function ProductComponent() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    productService.getAllProducts()
      .then((response) => {
        setProducts(response.data);
      })
      .catch((error) => {
        console.error('Ürünleri getirirken hata oluştu:', error);
      });
  }, []);

  return (
    <div>
      <h1>Ürünler</h1>
      <ul>
        {products.map((product) => (
          <li key={product.id}>{product.name} - {product.price} TL</li>
        ))}
      </ul>
    </div>
  );
}

export default ProductComponent;
