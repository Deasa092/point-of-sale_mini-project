import React from "react";
import { toRupiah } from "../../utils/formatter";

function Payment(props) {
  const { totalHarga, totalProduk } = props;
  return (
    <div className="fixed bottom-0 bg-slate-100 w-1/3">
      <div className="flex justify-between mx-10 ">
        <div className="title-produk">Total</div>
        <div className="title-produk">{toRupiah(totalHarga)}</div>
      </div>
      <div className="flex justify-center mb-5">
      <button className="add-btn">Bayar</button>
      </div>
      
    </div>
  );
}

export default Payment;
