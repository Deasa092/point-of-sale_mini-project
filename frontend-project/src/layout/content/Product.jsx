import React, { useEffect, useState } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {  faSort } from "@fortawesome/free-solid-svg-icons";
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
  }, [categoryId, search, sortBy, sortDirection]);

  const fetchData = async () => {
    try {
      let url = categoryId
        ? `/listproduct?categoryId=${categoryId}&title=${search}`
        : `/listproduct?title=${search}`;
      if (sortBy) {
        url += `&sortBy=${sortBy}&sortOrder=${sortDirection}`;
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

  const handleSort = (criteria) => {
    if (sortBy === criteria) {
      setSortDirection(sortDirection === "asc" ? "desc" : "asc");
    } else {
      setSortBy(criteria);
      setSortDirection("asc");
    }
  };

  return (
    <div className="w-2/3">
      <Header setSearch={setSearch} />
      <div className="mt-16 mb-16">
        <div className="flex items-center justify-between mx-5">
          <h1 className="title-page">Daftar Product</h1>
          <div className="flex justify-between gap-2 items-center cursor-pointer"  onClick={() => handleSort("price")}>
              <FontAwesomeIcon icon={faSort} className="text-2xl" />
              <p className="sort">sort</p>
            {/* <button onClick={() => handleSort("price")}>Sort by Price</button>
            <button onClick={() => handleSort("title")}>Sort by Title</button> */}
            <input
              type="text"
              className="search input"
              placeholder="Search... "
              onChange={(event) => setSearch(event.target.value)}
            />
          </div>
        </div>

        <div className="body-card">
          {product.map((product) => (
            <div key={product.id} className="">
              <ProductCard
                id={product.id}
                title={product.title}
                image={product.image}
                price={product.price}
              />
            </div>
          ))}
        </div>
        <Category category={category} handleOnClick={handleOnClick} />
      </div>
    </div>
  );
}
