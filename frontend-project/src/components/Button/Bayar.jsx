import React from "react";
import { toRupiah } from "../../utils/formatter";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { clearCart } from "../../store/reducer/cartSlice";

function Bayar(props) {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { totalHarga } = props;
  function handleBayar (){
    navigate("/pembayaran")
    console.log("Oke")
  }
  return (
    <div className="fixed bottom-0 bg-slate-100 w-1/3">
      <div className="flex justify-between mx-10 ">
        <div className="title-produk">Total</div>
        <div className="title-produk">{toRupiah(totalHarga)}</div>
      </div>
      <div className="flex justify-center mb-5">
      <button className="add-btn" onClick={handleBayar}>Bayar</button>
      </div>
      
    </div>
  );
}

export default Bayar;
