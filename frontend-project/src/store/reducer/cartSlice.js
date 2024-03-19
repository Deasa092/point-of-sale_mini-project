import { createSelector, createSlice } from "@reduxjs/toolkit";

const loadCart = () => {
     try{
          const cartData = localStorage.getItem("cart");
          return cartData ? JSON.parse(cartData) : [];
     } catch (error){
          console.error("Failed to load cart data from localStorage: ", error);
          return [];
     }
}
const calculateTotal = (cart) => {
     return cart?.reduce((total, item) => total + (item.price * item.qty), 0);
 }
const calculateTotalProducts =(cart) => {
     return cart.reduce((total, item) => total + item.qty, 0);
}
const cartSlice = createSlice({
  name: "data",
  initialState: {
    cart: loadCart(),
    total: calculateTotal(loadCart()),
    totalProducts: calculateTotalProducts(loadCart())
  },
  reducers: {
    addToCart: (state, action) => {
      const { qty } = action.payload;
      const dataCart = state.cart.findIndex((i) => i.id === action.payload.id);
      if (dataCart >= 0) {
        state.cart[dataCart].qty += qty;
      } else {
        const newData = { ...action.payload, qty: qty };
        state.cart.push(newData)
      }
      state.totalProducts = calculateTotalProducts(state.cart);
      state.total = calculateTotal(state.cart);
      localStorage.setItem("cart", JSON.stringify(state.cart));
    },
    incrementQty: (state, action) => {
      const dataCart = state.cart.findIndex((i) => i.id === action.payload.id);
      if (dataCart >= 0) {
        state.cart[dataCart].qty += 1;
      }
      state.totalProducts = calculateTotalProducts(state.cart);
      state.total = calculateTotal(state.cart);
      localStorage.setItem("cart", JSON.stringify(state.cart));
    },
    decrementQty: (state, action) => {
      const dataCart = state.cart.findIndex((i) => i.id === action.payload.id);
      if (dataCart >= 0) {
        state.cart[dataCart].qty -= 1;
      }
      state.totalProducts = calculateTotalProducts(state.cart);
      state.total = calculateTotal(state.cart);
      localStorage.setItem("cart", JSON.stringify(state.cart));
    },
    remove: (state, action) =>{
     state.cart =  state.cart.filter(i => i.id !== action.payload);
     state.totalProducts = calculateTotalProducts(state.cart);
     state.total = calculateTotal(state.cart);
     localStorage.setItem("cart", JSON.stringify(state.cart)); 
    },
   
  },
});

export const { addToCart, decrementQty, remove, incrementQty } = cartSlice.actions;
export const selectTotal = state => calculateTotal(state.data?.cart);
export const selectTotalProducts = state => state.data?.totalProducts;
export default cartSlice.reducer;
