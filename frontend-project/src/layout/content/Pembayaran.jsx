import React from "react";
import { useSelector } from "react-redux";

function Pembayaran() {
  const total = useSelector((state) => state.cart.total);
  const totalProducts = useSelector((state) => state.cart.totalProducts);
  return (
    <div className="border-l-4 border-gray-500 w-1/3 mr-3">
      <h1 className="title-page">Pembayaran</h1>
      <h3 className="flex justify-between mx-5"><p>Total</p><p>{total}</p></h3>
      <h3 className="flex">Dibayar 
          <input type="number" className="border-2 mx-3" prefix="Rp. "/>
      </h3>
      <button className="add-btn ">Selesaikan</button>
    </div>
  );
}

export default Pembayaran;
