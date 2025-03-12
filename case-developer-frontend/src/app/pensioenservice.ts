import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import {ɵElement, ɵValue} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class Pensioenservice {
  private apiUrl = 'http://localhost:8080/pensioenwaarde';

  constructor(private http: HttpClient) { }

  getPensioenwaarde(klant: ɵValue<ɵElement<number, null>> | undefined, pensoenleeftijd: ɵValue<ɵElement<number, null>> | undefined): Observable<number> {
    return this.http.get<number>(this.apiUrl+'?deelnemerId='+klant+'&pensioenleeftijd='+pensoenleeftijd);
  }
}
