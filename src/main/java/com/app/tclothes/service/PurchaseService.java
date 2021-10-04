package com.app.tclothes.service;

import com.app.tclothes.request.PurchaseRequest;
import com.app.tclothes.response.PurchaseResponse;

public interface PurchaseService {

	PurchaseResponse checkOut(PurchaseRequest purchase);

}
