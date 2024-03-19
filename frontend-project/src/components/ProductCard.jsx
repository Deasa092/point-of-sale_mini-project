import React, { useState } from 'react'
import { toRupiah } from '../utils/formatter'
import { useDispatch, useSelector } from 'react-redux';
import { addToCart } from '../store/reducer/cartSlice';

function ProductCard(data) {
  const {id, title, image, price} = data;
  const cart = useSelector(state => state.cart.cart);
  //console.log(cart);
  const dispatch = useDispatch();
  
  const [qty, setQty] = useState(1);
  const handlerAddToCart = () => {
    const payload = {
      ...data,
      qty
    };
    dispatch(addToCart(payload));
  };
  return (
    <div >
      <div className='card-produk' onClick={handlerAddToCart}>
          <div className='flex justify-center'>
          <img src={image} alt={title} className='w-64' />
          </div>
          <div>
               <h3 className='title-produk'>{title}</h3>
               <h4 className='price-produk'>{toRupiah(price)}</h4>
          </div>
      </div>
    </div>
  )
}

export default ProductCard
