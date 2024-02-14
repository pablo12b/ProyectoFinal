import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { InicioComponent } from './pages/inicio/inicio.component';
import { ProductoComponent } from './pages/producto/producto.component';
import { CarritoComponent } from './pages/carrito/carrito.component';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';

const routes: Routes = [
  { path: '', redirectTo: 'paginas/signup', pathMatch: 'full' }, // Redirige la ruta raíz a la página de registro
  {path:"paginas/signup", component: SignupComponent},
  {path:"paginas/inicio", component: InicioComponent},
  {path:"paginas/producto", component: ProductoComponent},
  {path:"paginas/carrito", component: CarritoComponent},
  {path:"paginas/login", component: LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
