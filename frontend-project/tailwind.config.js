/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        'raleway':['Raleway'],
        'lato': ['Lato'],
        'cinzel': ['Cinzel Decorative']
      },
    },
  },
  plugins: [],
}