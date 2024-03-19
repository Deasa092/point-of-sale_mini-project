Note: 
import React, { useEffect, useState } from "react";
import { baseUrl } from "../../utils/baseUrl";
import useSWR from "swr";
import ProductCard from "../../components/ProductCard";
import Header from "../Header";
import Category from "../../components/Category";

export default function Product() {
  const [categoryId, setCategoryId] = useState(null);
  const [search, setSearch] = useState("");
  const [product, setProduct] = useState([]);
  const [sortBy, setSortBy] = useState(true);
  const [sortDirection, setSortDirection] = useState("asc");
  const { data: category } = useSWR(`/listcategory`, (url) =>
    baseUrl.get(url).then((res) => res.data)
  );

  useEffect(() => {
    fetchData();
  }, [categoryId, search,  sortBy, sortDirection]);

  const fetchData = async () => {
    try {
      let url = `/listproduct?title=sepatu`;
      if (categoryId){
        url += `?categoryId=${categoryId}`;
      }
      if (search) {
        url += `${url.includes('?') ? '&' : '?'}title=${search}`;
      }
      if (sortBy) {
        url += `${url.includes('?') || url.includes('&') ? '&' : '?'}sortBy=price&sortOrder=${sortDirection}`;
      }

      const response = await baseUrl.get(url);
      setProduct(response.data);
    } catch (error) {
      console.error("Failed to fetch product data: ", error);
    }
  };

  if (!product) return <div>Loading...</div>;

  const handleOnClick = (categoryId) => {
    setCategoryId(categoryId);
  };

  const handleSortByPrice = () => {
    if (!sortBy) {
      setSortBy(true);
      setSortDirection("asc");
    } else {
      setSortDirection(sortDirection === "asc" ? "desc" : "asc");
    }
  };

  return (
    <div className="w-2/3">
      <Header setSearch={setSearch}/>
      <div className="mt-16">
        <h1 className="title-page">Daftar Product</h1>
        <input
        type="text"
        className="search input"
        placeholder="Search..."
        onChange={(event) => setSearch(event.target.value)}
      />
        <button onClick={handleSortByPrice}>
          {sortBy ? "Sort by Price (Ascending)" : "Sort by Price (Descending)"}
        </button>
        <div className="body-card">
          {product.map((product) => (
            <div key={product.id} className="">
              <ProductCard id={product.id} title={product.title} image={product.image} price={product.price} />
            </div>
          ))}
        </div>
        <Category category={category} handleOnClick={handleOnClick} />
      </div>
    </div>
  );
}

import React, { useEffect, useState } from "react";
import { baseUrl } from "../../utils/baseUrl";
import useSWR from "swr";
import ProductCard from "../../components/ProductCard";
import Header from "../Header";
import Category from "../../components/Category";

export default function Product() {
  const [categoryId, setCategoryId] = useState(null);
  const [search, setSearch] = useState("");
  const [product, setProduct] = useState([]);
  
  const { data: category } = useSWR(`/listcategory`, (url) =>
    baseUrl.get(url).then((res) => res.data)
  );

  useEffect(() => {
    fetchData();
  }, [categoryId, search]);

  const fetchData = async () => {
    try {
      const url = categoryId
        ? `/listproduct?categoryId=${categoryId}&title=${search}`
        : `/listproduct?title=${search}`;
      const response = await baseUrl.get(url);
      setProduct(response.data);
    } catch (error) {
      console.error("Failed to fetch product data: ", error);
    }
  };

  if (!product) return <div>Loading...</div>;

  const handleOnClick = (categoryId) => {
    setCategoryId(categoryId);
     console.log(categoryId)
  };

  const handleSearch = (e) => {
    setSearch(e.target.value.toLowerCase());
    console.log(setSearch);
  };

  return (
    <div className="w-2/3">
      <Header handleSearch={handleSearch} />
      <div className="mt-16">
        <h1 className="title-page">Daftar Product</h1>
         <input type="text" onChange={handleSearch} className="search input" />
        <div className="body-card">
          {product.map((product) => (
            <div key={product.id} className="">
              <ProductCard id = {product.id} image={product.image} price = {product.price} title = {product.title} />
            </div>
          ))}
        </div>
        <Category category={category} handleOnClick={handleOnClick} />
      </div>
    </div>
  );
}
