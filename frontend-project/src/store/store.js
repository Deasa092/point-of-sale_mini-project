import { configureStore } from "@reduxjs/toolkit";
import cartSlice from "./reducer/cartSlice";

const store = configureStore({
     reducer:{
          cart : cartSlice,
     },
})

export default store