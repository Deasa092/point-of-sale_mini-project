import { useEffect,  useState } from "react";
import { listProducts } from "../service/ProductsService";
import {toRupiah} from "../utils/formatter"

export default function Home() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
   listProducts().then((response) => {
     setProducts(response.data)
   }).catch(error =>{
     console.error(error)
   })
     
  }, []);

  return (
    <div className="w-2/3">
      <div className="header">
        <h2 className="title-page">Daftar Produk</h2>
        <div className="flex items-center">
          <p className="sort">Sort</p>
        <input type="text" className="search input" placeholder="Search" />
        </div>
        
      </div>
      <div className="body-card">
        {products.map((product) => (
          <div key={product.id} className="card-produk">
               <div className="flex justify-center">
               <img src={product.image} alt="" className="w-52" />
               </div>
            <div className="">
            <p className="title-produk" >{product.title}</p>
            <p className="price-produk "> {toRupiah(product.price)}</p>
            </div>

          </div>
        ))}
      </div>
    </div>
  );
}
