import React, { useEffect, useState } from 'react';
import stockService from './stockService';

function StockComponent() {
  const [stocks, setStocks] = useState([]);

  useEffect(() => {
    stockService.getAllStocks()
      .then((response) => {
        setStocks(response.data);
      })
      .catch((error) => {
        console.error('Stokları getirirken hata oluştu:', error);
      });
  }, []);

  return (
    <div>
      <h1>Stoklar</h1>
      <ul>
        {stocks.map((stock) => (
          <li key={stock.id}>{stock.name} - {stock.quantity} adet</li>
        ))}
      </ul>
    </div>
  );
}

export default StockComponent;
