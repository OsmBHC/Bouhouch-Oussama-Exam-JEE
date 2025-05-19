export interface CreditDTO {
  id?: number;
  requestDate?: Date;
  status: string;
  acceptationDate?: Date;
  amount: number;
  duration?: number;
  interestRate: number;
  clientId: number;
  type?: string;
  motif?: string;
  propertyType?: string;
  companyName?: string;
}