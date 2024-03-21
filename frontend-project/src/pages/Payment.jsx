import React from "react";
import { useSelector } from "react-redux";
import { toRupiah } from "../utils/formatter";
import Rincian from "../layout/content/RincianPembayaran";
import Pembayaran from "../layout/content/Pembayaran";

function Payment() {
  return (
   <div className="flex min-h-[860px]">
    <Rincian />
    <Pembayaran />
   </div>
  );
}

export default Payment;
