package com.jieli.jl_bt_ota.model.response;

import com.jieli.jl_bt_ota.model.base.CommonResponse;

/* loaded from: classes2.dex */
public class FirmwareUpdateStatusResponse extends CommonResponse {
    private int result;

    public FirmwareUpdateStatusResponse(int i) {
        this.result = i;
    }

    public int getResult() {
        return this.result;
    }

    @Override // com.jieli.jl_bt_ota.model.base.CommonResponse
    public String toString() {
        return "FirmwareUpdateStatusResponse{rawData size =" + (getRawData() == null ? 0 : getRawData().length) + "\nxmOpCode=" + getXmOpCode() + "\nresult=" + this.result + '}';
    }
}
