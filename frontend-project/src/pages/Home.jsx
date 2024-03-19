import React from 'react'
import Product from '../layout/content/Product'
import Header from '../layout/Header'
import Cart from './Cart'

function Home({handleSearch}) {
  
  return (
    <div>
      <Header handleSearch={handleSearch} />
      <div className='flex'>
      <Product/>
      <Cart />
      </div>
    
    </div>
  )
}

export default Home
