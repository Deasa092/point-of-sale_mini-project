import React from "react";
import { useSelector } from "react-redux";
import { toRupiah } from "../../utils/formatter";


function Rincian() {
  const cart = useSelector((state) => state.cart.cart);
  console.log(cart);

  return (
    <div className="w-2/3 ">
      <div className="title-page">Rincian Pesanan</div>
      <div className="body">
        {cart.map((item) => (
          <div className="cart-body px-10 py-3 m-5" key={item.id} >
            <img src={item.image} className="w-40" />
            <table className="text-xl mx-6 max-lg:text-base">
              <tr>
                <td className="w-[500px] ">{item.title}</td>
                <td className="text-center w-32">{item.qty}x</td>
                <td className="text-center w-80">
                  {toRupiah(item.subTotal)}
                </td>
              </tr>
              <h3 className="py-3">{toRupiah(item.price)}</h3>
            </table>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Rincian;
