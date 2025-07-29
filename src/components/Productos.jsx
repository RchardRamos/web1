import banner from "../assets/img/banner.jpg";

export default function Productos({ agregarAlCarrito }) {
  const productos = [
    {
      nombre: "Caña Serrana",
      descripcion: "Ron oscuro y dulce elaborado en la Sierra, con un toque cálido de especias locales.",
      precio: 15.0,
      imagen: "cana-serrana.jpg",
    },
    {
      nombre: "Chicha de Oro",
      descripcion: "Fermentado ancestral inspirado en las tradiciones andinas del Ecuador.",
      precio: 10.0,
      imagen: "chicha-de-oro.jpg",
    },
    {
      nombre: "Mistela de Guayusa",
      descripcion: "Licor suave y energizante con sabor amazónico, ideal para ocasiones especiales.",
      precio: 12.0,
      imagen: "mistela-de-guayusa.jpg",
    },
    {
      nombre: "Cerveza Quitu",
      descripcion: "Cerveza ecuatoriana con quinua andina, perfecta para quienes buscan lo natural.",
      precio: 13.0,
      imagen: "cerveza-quitu.jpg",
    },
  ];

  const cantidadInicial = 1;
  const cantidades = Array(productos.length).fill(cantidadInicial);

  const incrementarCantidad = (index) => {
    cantidades[index] += 1;
    actualizarVista();
  };

  const decrementarCantidad = (index) => {
    if (cantidades[index] > 1) {
      cantidades[index] -= 1;
      actualizarVista();
    }
  };

  const actualizarVista = () => {
    const actual = document.querySelector("#productos");
    if (actual) actual.dispatchEvent(new Event("actualizar"));
  };

  const añadirProducto = (producto, cantidad) => {
    for (let i = 0; i < cantidad; i++) {
      agregarAlCarrito(producto);
    }
  };

  return (
    <div
      id="productos"
      className="min-h-screen bg-cover bg-center py-10 px-4"
      style={{ backgroundImage: `url(${banner})` }}
    >
      <h1 className="text-3xl font-bold text-white text-center mb-10">Nuestros Productos</h1>
      <div className="grid md:grid-cols-2 lg:grid-cols-4 gap-8 max-w-7xl mx-auto">
        {productos.map((producto, index) => (
          <div key={index} className="bg-black/90 text-white p-6 rounded-2xl shadow-lg flex flex-col items-center">
            <img
              src={`/src/assets/img/${producto.imagen}`}
              alt={producto.nombre}
              className="h-40 object-contain mb-4 rounded-lg"
            />
            <h2 className="text-lg font-bold text-center text-blue-300">{producto.nombre}</h2>
            <p className="text-sm text-center mt-2">{producto.descripcion}</p>
            <p className="text-green-300 font-semibold mt-2">S/ {producto.precio.toFixed(2)}</p>
            <div className="flex items-center gap-2 mt-4">
              <button
                className="bg-green-600 px-2 rounded text-white text-xl"
                onClick={() => decrementarCantidad(index)}
              >
                -
              </button>
              <span className="bg-white text-black px-3 py-1 rounded font-bold">{cantidades[index]}</span>
              <button
                className="bg-green-600 px-2 rounded text-white text-xl"
                onClick={() => incrementarCantidad(index)}
              >
                +
              </button>
            </div>
            <button
              className="mt-3 bg-yellow-400 hover:bg-yellow-500 text-black font-bold px-4 py-2 rounded-xl"
              onClick={() => añadirProducto(producto, cantidades[index])}
            >
              Añadir al carrito
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
