import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import { Provider } from "react-redux";
import store from "./store/store";
import ProductList from "./pages/ProductList";

function App() {
  return (
    <>
      <Provider store={store}>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/productlist" element={<ProductList />} />
          </Routes>
        </BrowserRouter>
      </Provider>
    </>
  );
}

export default App;
