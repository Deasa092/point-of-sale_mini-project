import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMinus, faPlus, faTrash } from "@fortawesome/free-solid-svg-icons";
import { useDispatch, useSelector } from "react-redux";
import { toRupiah } from "../utils/formatter";
import { clearCart, decrementQty, incrementQty, remove } from "../store/reducer/cartSlice";
import Bayar from "../components/Button/Bayar";
import { useNavigate } from "react-router-dom";

export default function Cart() {
  const navigate = useNavigate()
  const cart = useSelector((state) => state.cart.cart);
  const total = useSelector((state) => state.cart.total);
  const totalProducts = useSelector((state) => state.cart.totalProducts);
  const dispatch = useDispatch();
  console.log(cart)
 
  return (
    <div className="border-l-4 mt-16 w-1/3 mr-3">
      <h1 className="title-page">Daftar Pesanan</h1>
      <button onClick={()=>dispatch(clearCart())}>delete all...</button>
      <div className="my-5 mx-2 ">
        {cart.length === 0 && <p className="title-produk ">Keranjang Kosong</p>}
        <div className="">
          {cart.map((item) => (
            <div className="cart-body" key={item.id}>
              <div className="px-3">
                <h3 className="title-cart">{item.title} </h3>
                <div className="oneline justify-between max-lg:block gap-10 ">
                  <div className="w-48">
                    {toRupiah(item.price)}/unit
                  </div>
                  <div className="oneline justify-between gap-5">
                    <div className="oneline gap-3">
                      <FontAwesomeIcon icon={faMinus}  className="plus-minus-btn"
                        onClick={   item.qty === 1  ? () => dispatch(remove(item.id))  : () => dispatch(decrementQty(item))
                        }
                      />
                      <h3>{item.qty}</h3>
                      <FontAwesomeIcon
                        icon={faPlus}
                        className="plus-minus-btn"
                        onClick={() => dispatch(incrementQty(item))}
                      />
                    </div>
                    <FontAwesomeIcon
                      icon={faTrash}
                      className="delete"
                      onClick={() => dispatch(remove(item.id))}
                    />
                  </div>
                  <h3 className="price-produk">
                    {toRupiah(item.subTotal)}
                  </h3>
                </div>
              </div>
            </div>
          ))}
        </div>
        <div>
          <Bayar totalHarga={total} totalProduk={totalProducts} />
        </div>
      </div>
    </div>
  );
}
