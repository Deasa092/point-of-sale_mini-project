
export default function Category(props) {
 const {category, handleOnClick} = props
  return (
    <div className="footer">
      <div className="flex justify-center gap-1 m-2">
        <button className="category-btn active:bg-blue-500"  onClick={() => handleOnClick(null)}>All</button>
      {category?.map((category) => (
        <div key={category.id} onClick={() => handleOnClick(category.id)}>
          <button className="category-btn active:bg-blue-500" >
          {category.name}
          </button>
        </div>
      ))}
      </div>
    </div>
  );
}
