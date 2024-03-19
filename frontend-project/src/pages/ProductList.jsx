import React from 'react'
import { baseUrl } from '../utils/baseUrl';
import useSWR from 'swr';

function ProductList() {
     const fetchData = (url) => baseUrl.get(url).then((response) => response.data);
     const { data: product, mutate } = useSWR(
     `/listproduct`,
       fetchData
     );
  return (
    <div className='mx-10'>
     <h1 className='title-page'>List Product</h1>
      {
        product?.map((product) => (
          <div className='grid grid-cols-4 border-b border-gray-500 items-center'>
               <h3 className='title-produk'>{product.id}</h3>
              <img src={product.image} alt={product.title} className='w-24 mx-4' />
               <h3 className='title-produk'>{product.title}</h3>
               <h3 className='title-produk'>{product.price}</h3>
          </div>

        ))  
      }
    </div>
  )
}

export default ProductList
