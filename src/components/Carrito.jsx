import banner from "../assets/img/banner.jpg";

export default function Carrito({ carrito, vaciarCarrito }) {
  const total = carrito.reduce((acc, item) => acc + item.precio, 0);

  return (
    <div
      className="min-h-screen bg-cover bg-center p-8"
      style={{ backgroundImage: `url(${banner})` }}
    >
      <h1 className="text-3xl font-bold text-white mb-6 text-center">Tu Carrito</h1>
      {carrito.length === 0 ? (
        <p className="text-white text-center text-lg">No hay productos en el carrito.</p>
      ) : (
        <div className="space-y-4">
          {carrito.map((producto, index) => (
            <div key={index} className="bg-white/90 rounded-xl shadow p-4 flex items-center gap-4">
              <img
                src={`/src/assets/img/${producto.imagen}`}
                alt={producto.nombre}
                className="w-20 h-20 object-cover rounded-lg"
              />
              <div>
                <h2 className="text-lg font-semibold">{producto.nombre}</h2>
                <p>${producto.precio.toFixed(2)}</p>
              </div>
            </div>
          ))}
          <div className="bg-white/90 rounded-xl p-4 mt-4">
            <h3 className="text-xl font-bold">Total: ${total.toFixed(2)}</h3>
            <button
              onClick={vaciarCarrito}
              className="mt-3 bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-xl"
            >
              Vaciar Carrito
            </button>
          </div>
        </div>
      )}
    </div>
  );
}
