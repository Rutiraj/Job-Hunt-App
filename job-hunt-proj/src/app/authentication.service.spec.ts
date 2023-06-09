import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule,HttpTestingController} from '@angular/common/http/testing';
import { AuthenticationService } from './authentication.service';
import { HttpClient } from '@angular/common/http';

describe('AuthenticationService', () => {
  let service: AuthenticationService;
  let http:HttpClient;
  let httpController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule],
      providers:[AuthenticationService]
    });
    service = TestBed.inject(AuthenticationService);
    http=TestBed.inject(HttpClient);
    httpController=TestBed.inject(HttpTestingController);
  });

  it('service created', () => {
    expect(service).toBeDefined();
    });

  it('login api',()=>{
    const testData=true;
    const inputData={
      userName:"supriya",
      password:"1234"
    }
  })


});
