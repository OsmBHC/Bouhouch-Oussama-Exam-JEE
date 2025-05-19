import { Routes } from '@angular/router';
import { ClientsComponent } from './clients/clients.component';
import { NewClientComponent } from './new-client/new-client.component';
import { CreditsComponent } from './credits/credits.component';
import { NewCreditComponent } from './new-credit/new-credit.component';

export const routes: Routes = [
  { path: 'clients', component: ClientsComponent },
  { path: 'new-client', component: NewClientComponent },
  { path: 'credits', component: CreditsComponent },
  { path: 'credits/:clientId', component: CreditsComponent },
  { path: 'new-credit', component: NewCreditComponent },
  { path: '', redirectTo: '/clients', pathMatch: 'full' }, // Optional: default route
  { path: '**', redirectTo: '/clients' } // Optional: catch-all route
];