import React, { useState } from 'react';

const SortDropdown = ({ handleSort }) => {
  const [sortBy, setSortBy] = useState('');
  const [sortDirection, setSortDirection] = useState('asc'); // Default arah sorting adalah ascending

  const handleSortChange = (event) => {
    const selectedSort = event.target.value;
    setSortBy(selectedSort);
    handleSort(selectedSort, sortDirection);
  };

  const toggleSortDirection = () => {
    const newSortDirection = sortDirection === 'asc' ? 'desc' : 'asc';
    setSortDirection(newSortDirection);
    handleSort(sortBy, newSortDirection);
  };

  return (
    <div>
      <label htmlFor="sort">Sort By:</label>
      <select
        id="sort"
        value={sortBy}
        onChange={handleSortChange}
        className="block w-full mt-1 py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
      >
        <option value="">Select</option>
        <option value="price">Price</option>
        <option value="title">Title</option>
      </select>
      <div className="mt-2">
        <button
          className={`px-4 py-2 border border-gray-300 rounded-md bg-white text-gray-700 hover:bg-gray-100 focus:outline-none`}
          onClick={toggleSortDirection}
        >
          {sortDirection === 'asc' ? 'Ascending' : 'Descending'}
        </button>
      </div>
    </div>
  );
};

export default SortDropdown;
