import { Component, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './register.html',
})
export class Register {
  name = '';
  email = '';
  cpf = '';
  telephone = '';
  password = '';
  showPassword = signal(false);
  isLoading = signal(false);

  constructor(private router: Router) {}

  onCpfInput(event: any) {
    const input = event.target as HTMLInputElement;
    let value = input.value.replace(/\D/g, '');
    if (value.length > 11) value = value.slice(0, 11);
    
    // User requested specifically "input de número de 11 dígitos"
    // and explicitly requested format ONLY for the telephone.
    // So we keep CPF as raw digits, just limited to 11.
    this.cpf = value;
    input.value = value;
  }

  onPhoneInput(event: any) {
    const input = event.target as HTMLInputElement;
    let value = input.value.replace(/\D/g, '');
    
    // Limit to 11 digits (DDD + 9 digits)
    if (value.length > 11) value = value.slice(0, 11);

    // Apply mask (00)00000-0000
    if (value.length > 10) {
      value = value.replace(/^(\d\d)(\d{5})(\d{4}).*/, '($1)$2-$3');
    } else if (value.length > 6) {
      value = value.replace(/^(\d\d)(\d{4})(\d{0,4}).*/, '($1)$2-$3');
    } else if (value.length > 2) {
      value = value.replace(/^(\d\d)(\d{0,5}).*/, '($1)$2');
    } else if (value.length > 0) {
        value = value.replace(/^(\d*)/, '($1');
    }

    this.telephone = value;
    input.value = value;
  }

  togglePasswordVisibility(): void {
    this.showPassword.update((v) => !v);
  }

  onRegister(): void {
    if (!this.name || !this.email || !this.password) return;
    
    // For now, simple mock register
    this.isLoading.set(true);
    setTimeout(() => {
        this.isLoading.set(false);
        this.router.navigate(['/login']);
    }, 1000);
  }
}
